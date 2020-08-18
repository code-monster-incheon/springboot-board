package com.algo.inc.web.controller.api.reply;

import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reply")
public class ReplyApiController {

    private final ReplyService replyService;

    // Create, boardId번 게시글에 댓글을 새로 추가한다.
    @PostMapping("/{boardId}")
    public Long registerReply(@PathVariable Long boardId,
                              @RequestBody ReplySaveRequestDto replySaveRequestDto,
                              Authentication authentication){

        return replyService.registerReply(boardId, replySaveRequestDto, (UserDetails) authentication.getPrincipal());
    }

    // Read, boardId번 게시글에 달린 모든 댓글을 읽어온다
    @GetMapping("/{boardId}")
    public List<ReplyResponseDto> findReplyOnPost(@PathVariable Long boardId){
        return replyService.findReplyOnPost(boardId);
    }

    // Update, replyId번 댓글을 수정한다.
    @PutMapping("/{replyId}")
    public Long updateReply(@PathVariable Long replyId,
                            @RequestBody ReplySaveRequestDto replySaveRequestDto){

        return replyService.updateReply(replyId,replySaveRequestDto);
    }

    // Delete, replyId번 댓글을 삭제한다.
    @DeleteMapping("/{replyId}")
    public void deleteReply(@PathVariable Long replyId){
        replyService.deleteReply(replyId);
    }

}
