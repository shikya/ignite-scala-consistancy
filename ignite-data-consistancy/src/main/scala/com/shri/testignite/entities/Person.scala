package com.shri.testignite.entities

import scala.util.Random

case class Person(id: Long, fname: String, lname: String, bio: String)

object Person {
  /**
    * Get a person with random id, fname, lname and bio
    * @return Person
    */
  def getRandom(): Person = new Person(0, Random.alphanumeric.take(5).mkString, Random.alphanumeric.take(5).mkString, Random.alphanumeric.take(30).mkString)

  /**
    * Get a person with random fname, lname and bio
    * @param i is the person id
    * @return Person
    */
  def getRandom(i: Long): Person = new Person(i, Random.alphanumeric.take(5).mkString, Random.alphanumeric.take(5).mkString, Random.alphanumeric.take(30).mkString)
}