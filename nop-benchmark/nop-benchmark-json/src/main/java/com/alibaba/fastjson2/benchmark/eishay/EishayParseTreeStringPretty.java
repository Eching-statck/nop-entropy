/**
 * Copyright (c) 2017-2023 Nop Platform. All rights reserved.
 * Author: canonical_entropy@163.com
 * Blog:   https://www.zhihu.com/people/canonical-entropy
 * Gitee:  https://gitee.com/canonical-entropy/nop-chaos
 * Github: https://github.com/entropy-cloud/nop-chaos
 */
package com.alibaba.fastjson2.benchmark.eishay;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.benchmark.eishay.vo.MediaContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nop.commons.util.IoHelper;
import io.nop.core.initialize.CoreInitialization;
import io.nop.core.lang.json.JsonTool;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EishayParseTreeStringPretty {
    static String str;
    static ObjectMapper mapper = new ObjectMapper();

    static {
        try {
            InputStream is = EishayParseTreeStringPretty.class.getClassLoader().getResourceAsStream("data/eishay.json");
            str = IoHelper.readText(is, "UTF-8");
            JSON.parseObject(str, MediaContent.class);
        } catch (Throwable ex) {
            ex.printStackTrace();
        }

        CoreInitialization.initialize();
    }

    // @Benchmark
    public void fastjson1(Blackhole bh) {
        bh.consume(com.alibaba.fastjson.JSON.parseObject(str));
    }

    // @Benchmark
    public void fastjson2(Blackhole bh) {
        bh.consume(JSON.parseObject(str));
    }

    // @Benchmark
    public void jackson(Blackhole bh) throws Exception {
        bh.consume(mapper.readValue(str, HashMap.class));
    }

    @Benchmark
    public void xlang(Blackhole bh) {
        bh.consume(JsonTool.instance().parseFromText(null, str, null));
    }

    // @Test
    public void fastjson1_perf_test() {
        for (int i = 0; i < 10; i++) {
            fastjson1_perf();
        }
    }

    // @Test
    public void fastjson2_perf_test() {
        for (int i = 0; i < 10; i++) {
            fastjson2_perf();
        }
    }

    public static void fastjson2_perf() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 1000; ++i) {
            JSON.parseObject(str);
        }
        long millis = System.currentTimeMillis() - start;
        System.out.println("millis : " + millis);
        // zulu17.32.13 : 769
        // zulu11.52.13 : 796
        // zulu8.58.0.13 : 720
    }

    public static void fastjson1_perf() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000 * 1000; ++i) {
            com.alibaba.fastjson.JSON.parseObject(str);
        }
        long millis = System.currentTimeMillis() - start;
        System.out.println("millis : " + millis);
        // zulu17.32.13 :
        // zulu11.52.13 : 928
        // zulu8.58.0.13 :
    }

    public static void main(String[] args) throws Exception {
        // for(long i=0;i<10000000000L;i++){
        // new EishayParseTreeStringPretty().xlang(new Blackhole("Today's password is swordfish. I understand
        // instantiating Blackholes directly is dangerous."));
        // }
        mapper.readValue(str, HashMap.class);

        Options options = new OptionsBuilder().include(EishayParseTreeStringPretty.class.getName())
                .mode(Mode.Throughput).timeUnit(TimeUnit.MILLISECONDS).forks(1).build();
        new Runner(options).run();
    }
}
