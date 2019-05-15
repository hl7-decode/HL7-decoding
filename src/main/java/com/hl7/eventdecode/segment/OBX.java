package com.hl7.eventdecode.segment;

/**
 * OBX 字段信息处理
 * OBX-1 序号
 * OBX-2 结果值类型
 * OBX-3 结果标识（若为IMP,则Value type内容为CE,Observation value内容为诊断印象。若为REC,则Value type内容为CE，Observation value内容为诊断建议。若为GDT，则Value type内容为TX，Observation value内容为诊断所见。若为RRP，则Value type内容为RP，Observation value内容为报告存储路径。若为ZMF，则Value type内容为ED;Observation value的格式为ED，内容为主报告文件（文件数据使用UU Encoded 进行编码）。若 为 ZRF，贝 lj Value type 内容 为 ED，Observation value 的 格式为 ED，内容为相关报 告文件（文件数据使用 UU Encoded 进行编码））
 * OBX-4 子ID
 * OBX-5 结果值（类型由 OBX-3(Value type)的值确定。 ）
 * OBX-8 阴阳性标志（阴阳性标志，A:阳性，N: 阴性，其他状态为空。Observation identifier 为 &GDT 时有该字段。 ）
 * OBX-11 结果标志
 */

public class OBX {
    
}