package com.careline.interview.test.mission2;

import com.careline.interview.test.mission2.vo.ComputeVo;
import com.careline.interview.test.mission2.vo.ReqComputeVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.IntSummaryStatistics;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mission2")
public class Mission2Controller {
    @PostMapping("/compute")
    public ComputeVo compute(@RequestBody ReqComputeVo req) {
        ComputeVo vo = new ComputeVo();

        IntSummaryStatistics statistics = req.getNumList().stream().collect(Collectors.summarizingInt(i -> i));
        vo.setSum(statistics.getSum());
        vo.setMax(statistics.getMax());
        vo.setMin(statistics.getMin());
        vo.setAvg(BigDecimal.valueOf(statistics.getAverage()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        req.getNumList().sort(Integer::compareTo);
        vo.setSorted_list(req.getNumList());
        return vo;
    }
}
