package com.yan.demo.wesockter.message;

import com.yan.demo.common.enums.ChannelsEnum;
import com.yan.demo.wesockter.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：yanfuming
 * @description：TODO
 * @date ：2021/11/15 1:58 下午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnOpenMessage  {
    /**
     *初始链接参数==订阅关闭频道
     */
    String channel;




}
