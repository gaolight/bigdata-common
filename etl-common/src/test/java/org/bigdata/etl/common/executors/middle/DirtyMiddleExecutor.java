package org.bigdata.etl.common.executors.middle;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;
import org.bigdata.etl.common.annotations.ETLExecutor;
import org.bigdata.etl.common.configs.DirtyConfig;
import org.bigdata.etl.common.executors.MiddleExecutor;

import java.util.Collection;
import java.util.Optional;


/**
 * Author: GL
 * Date: 2022-04-22
 */
@Slf4j
@ETLExecutor("dirty")
public class DirtyMiddleExecutor implements MiddleExecutor<SparkContext, RDD<String>, DirtyConfig> {

    @Override
    public void init(SparkContext engine, DirtyConfig config) {
        log.info("DirtyMiddle init, config: {}", config);
    }

    @Override
    public Collection<RDD<String>> process(Collection<RDD<String>> dataCollection, DirtyConfig config) {
        log.info("DirtyMiddle process, config: {}", config);
        final Optional<RDD<String>> first = dataCollection.stream().findFirst();
        first.ifPresent((rdd) -> log.info("middle collect: {}", (Object) rdd.collect()));
        return dataCollection;
    }

    @Override
    public void close(SparkContext engine, DirtyConfig config) {
        log.info("DirtyMiddleExecutor close, config: {}", config);
    }

    @Override
    public boolean check(DirtyConfig config) {
        log.info("DirtyMiddleExecutor check, config: {}", config);
        return true;
    }
}