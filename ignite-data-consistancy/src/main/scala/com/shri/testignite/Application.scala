package com.shri.testignite

import com.shri.testignite.entities.Person
import org.apache.ignite.cache.query.ScanQuery
import org.apache.ignite.{Ignite, IgniteCache, Ignition}

import scala.util.{Failure, Success, Try}

object Application extends App {

//  Application configuration
  val cacheConfigLocation = "ignite-conf.xml"
  println("[START] Starting ignite")
  val ignite: Ignite = Ignition.start(cacheConfigLocation)
  println("[END] Starting ignite")

  // Get cache
  println("Getting person cache")
  val personIgniteCache: IgniteCache[Long, Person] = ignite.cache("person")
  println("Getting personcount cache")
  val personCountCache: IgniteCache[Int, Long] = ignite.cache("personcount")

  //  If there is no person count then make it 1
  var counter: Long = Try(personCountCache.get(1)) match {
    case Success(a) => a
    case Failure(_) => 1
  }

  //  Add some records to ignite
  //  200000+ are recommanded
  println("This will take some time")
  println("[START] injecting fake data.")
  for(a <- 1 to 200) {
    val person = Person.getRandom(a)
    personIgniteCache.put(counter, person)
    counter = counter + 1
    personCountCache.put(1, counter)
  }
  println("[STOP] injecting fake data")

  println(s"Counter is set to ${counter} and Records present are ${personIgniteCache.withKeepBinary[Long, Person]().query(new ScanQuery()).getAll.size}")
  //  Print some records from ignite
  //  Get all records
  println("[START] Checking records")
  for(a <- 1 to 10000) {
    var size = Try(personIgniteCache.withKeepBinary[Long, Person]().query(new ScanQuery()).getAll.size) match {
      case Success(a) => a
      case Failure(ex: Exception) => {
        println(s"[ERROR] While fetching ${ex.getMessage}")
        ex.printStackTrace()
        -1
      }
    }
    if(size == counter)
      print(".") // If everything is Ok I single dot will printed. Just to show that it is working in the background.
    else
    println(s"\n[ERROR] from ignite ${size} vs actual ${counter}") // If the number of records are diffrent in both size then it will throw error.
    Thread.sleep(50)
  }
  println("[STOP] Checking records")

  println("[START] Stoping ignite")
  Ignition.stop(true)
  println("[END] Stoping ignite")
}