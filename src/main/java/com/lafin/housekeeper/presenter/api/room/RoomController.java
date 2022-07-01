package com.lafin.housekeeper.presenter.api.room;

import com.lafin.housekeeper.presenter.api.room.request.CreateRoomRequest;
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

    private final RoomAdapter adapter;

    @GetMapping("/{page}")
    public ResponseEntity<ResponseModel> list(@PathVariable int page,
                                              @RequestHeader(value = "X-AUTH-TOKEN") String accessToken) throws InvalidInputException {
        adapter.verify(accessToken);
        var paging = Paging.builder()
                .page(page)
                .build();
        return ResponseUtil.json(adapter.getRooms(paging));
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseModel> add(@RequestHeader(value = "X-AUTH-TOKEN") String accessToken,
                                             @RequestBody CreateRoomRequest request) throws InvalidInputException {
        adapter.verify(accessToken);
        return ResponseUtil.json(adapter.addRoom(request));
    }
}
