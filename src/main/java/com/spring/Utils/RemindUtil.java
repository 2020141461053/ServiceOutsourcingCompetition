package com.spring.Utils;

public class RemindUtil {

    public static String getRemind(String url) {
        System.out.println(url);
        if ("translate".equals(url)) {
            return "请你扮演一位翻译师，将我所给的内容翻译为" + "，下面是具体内容：";
        } else if ("outline".equals(url)) {
            return "我现在正在制作一个PPT，请你根据以下内容给出一个PPT大纲，并给出一份" + "风格的讲稿：";
        } else if ("correct".equals(url)) {
            return "请你扮演一位老师，能够根据给出的题目分析题目得分点，并根据我的回答指出我的不足，给出正确答案以及修改意见。下面是具体内容：";
        } else if ("analysis".equals(url)) {
            return "请你扮演一位AI数据分析师，能够根据我给出的数据运用stata软件进行数据分析，并输出数据集的信度和效度分析结果，请以一个数据+一个结论的形式给出分析结果。以下是数据要求：";
        } else if ("testCode".equals(url)) {
            return "请检查这段编码是否存在逻辑错误、语法错误，如果存在错误，请指出错误所在位置并给出修改意见：";
        } else if ("summarize".equals(url)) {
            return "请总结这篇文章中的主要思想、创新点以及难点，并给出这篇文章领域话题的研究现状、热点方向、未来展望、文献综述等信息：";
        } else if ("modify".equals(url)) {
            return "请你扮演一位AI写作导师，润色我所给出的内容，使其更加正式，更合逻辑，并且具有论文的风格。注意，除了给出润色修改之后的内容，还请指明修订的版本中具体修改了哪些段落的哪几句话。下面是具体内容：";
        } else if ("schedule".equals(url)) {
            return "请根据以下任务以及对应的时间规划我的日常计划：";
        } else if ("plan".equals(url)) {
            return "请根据我提供的活动安排完成一份活动策划,包括时间、地点以及主要的活动安排：";
        } else {
            throw new RuntimeException("无此任务");
        }
    }
    }

