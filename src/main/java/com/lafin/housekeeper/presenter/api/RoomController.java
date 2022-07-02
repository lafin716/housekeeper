package com.lafin.housekeeper.presenter.api;

import com.lafin.housekeeper.presenter.api.adapter.RoomAdapter;
import com.lafin.housekeeper.presenter.api.adapter.UserAdapter;
import com.lafin.housekeeper.presenter.api.request.CreateRoomRequest;
import com.lafin.housekeeper.shared.contract.domain.usecase.InvalidInputException;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.Paging;
import com.lafin.housekeeper.shared.contract.presenter.viewmodel.ResponseModel;
import com.lafin.housekeeper.shared.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room")
public class RoomController {

    private final UserAdapter userAdapter;

    private final RoomAdapter adapter;

    @GetMapping("/list/{page}")
    public ResponseEntity<ResponseModel> list(@PathVariable int page,
                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        userAdapter.verify(accessToken);
        var paging = Paging.builder()
                .page(page)
                .build();
        return ResponseUtil.json(adapter.getRooms(paging));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateRoomRequest request) throws InvalidInputException {
        var userId = userAdapter.verify(accessToken);
        request.setUserId(userId);
        return ResponseUtil.json(adapter.addRoom(request));
    }
}
