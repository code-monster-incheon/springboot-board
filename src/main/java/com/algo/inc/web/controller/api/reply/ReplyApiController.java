package com.algo.inc.web.controller.api.reply;

import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/replies")
@Log4j2
public class ReplyApiController {

    private final ReplyService replyService;

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PostMapping("/{id}")
    public Long registerReply(@PathVariable Long id, @RequestBody ReplySaveRequestDto replySaveRequestDto)
    {
        log.info("registerReply : {}", id);
        return replyService.registerReply(id, replySaveRequestDto);
    }

    @GetMapping("/{id}")
    public List<ReplyResponseDto> findReplyOnPost(@PathVariable Long id)
    {
        return replyService.findReplyOnPost(id);
    }

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @PutMapping("/{replyId}")
    public Long updateReply(@PathVariable Long replyId, @RequestBody ReplySaveRequestDto replySaveRequestDto)
    {
        return replyService.updateReply(replyId,replySaveRequestDto);
    }

    @Secured(value = {"ROLE_GUEST", "ROLE_MANAGER", "ROLE_ADMIN"})
    @DeleteMapping("/{boardId}/{replyId}")
    public ResponseEntity<List<ReplyResponseDto>> deleteReply(@PathVariable Long replyId, @PathVariable Long boardId)
    {
        return new ResponseEntity<>(replyService.deleteReply(boardId, replyId), HttpStatus.OK);
    }

}
