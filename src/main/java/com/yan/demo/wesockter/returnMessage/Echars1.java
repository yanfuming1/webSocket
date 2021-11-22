package com.yan.demo.wesockter.returnMessage;

import com.yan.demo.wesockter.ReturnMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 2:38 下午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Echars1 extends EcharsBase implements Serializable {
    String name;
    Long value;
}
