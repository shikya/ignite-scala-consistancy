package com.shri.testignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;
import org.apache.ignite.resources.IgniteInstanceResource;

public class ClusterInitializer implements LifecycleBean {

    @IgniteInstanceResource
    private Ignite ignite;

    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        System.out.println("[START] Lifecyvle method "+ evt);
        if(evt == LifecycleEventType.AFTER_NODE_START && ignite.cluster().topology(ignite.cluster().topologyVersion()).size()>2) {
            System.out.println("Starting cluster");
            // Set cluster active
            ignite.cluster().active(true);
            // Add to baseline
            ignite.cluster().setBaselineTopology(ignite.cluster().topologyVersion());

            System.out.println("Cluster Started");
        }
        System.out.println("[END] Lifecyvle method "+ evt);
    }
}
