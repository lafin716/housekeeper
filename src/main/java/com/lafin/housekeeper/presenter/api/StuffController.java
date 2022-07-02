package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.presenter.api.adapter.StuffAdapter;
import com.lafin.housekeeper.presenter.api.adapter.UserAdapter;
import com.lafin.housekeeper.presenter.api.request.*;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.common.Paging;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stuff")
public class StuffController {

    private final UserAdapter userAdapter;

    private final StuffAdapter adapter;

    @GetMapping("/list/{page}")
    public ResponseEntity<ResponseModel> list(@PathVariable int page,
                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        var paging = Paging.of(page);
        return ResponseUtil.json(adapter.getStuffs(userId, paging));
    }

    @GetMapping("/{stuffId}")
    public ResponseEntity<ResponseModel> get(@PathVariable Long stuffId,
                                             @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        return ResponseUtil.json(adapter.getStuff(userId, stuffId));
    }

    @PutMapping("/use/{stuffId}")
    public ResponseEntity<ResponseModel> spend(@PathVariable Long stuffId,
                                               @RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                               @RequestBody SpendStuffRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        request.setStuffId(stuffId);
        return ResponseUtil.json(adapter.spendStuff(request));
    }

    @PutMapping("/fill/{stuffId}")
    public ResponseEntity<ResponseModel> fill(@PathVariable Long stuffId,
                                               @RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                               @RequestBody FillStuffRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        request.setStuffId(stuffId);
        return ResponseUtil.json(adapter.fillStuff(request));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateStuffRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        return ResponseUtil.json(adapter.addStuff(request));
    }

    @PutMapping("/{stuffId}")
    public ResponseEntity<ResponseModel> update(@PathVariable Long stuffId,
                                                @RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                                @RequestBody UpdateStuffRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        request.setStuffId(stuffId);
        return ResponseUtil.json(adapter.updateStuff(request));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long roomId,
                                                @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        return ResponseUtil.json(adapter.deleteStuff(userId, roomId));
    }
}
