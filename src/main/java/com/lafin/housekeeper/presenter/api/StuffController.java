package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.presenter.api.adapter.StuffAdapter;
import com.lafin.housekeeper.presenter.api.request.CreateStuffRequest;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stuff")
public class StuffController {

    private final StuffAdapter adapter;

//    @GetMapping("/{page}")
//    public ResponseEntity<ResponseModel> list(@PathVariable int page,
//                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
//        adapter.verify(accessToken);
//        var paging = Paging.builder()
//                .page(page)
//                .build();
//        return ResponseUtil.json(adapter.getRooms(paging));
//    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateStuffRequest request) throws InvalidInputException {
        adapter.verify(accessToken);
        return ResponseUtil.json(adapter.addStuff(request));
    }
}
