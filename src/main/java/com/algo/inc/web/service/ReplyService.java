package com.algo.inc.web.service;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.algo.inc.web.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // Create, 댓글 생성 : boardId, 댓글 내용, 유저 정보가 필요함
    public Long registerReply(Long boardId, ReplySaveRequestDto replySaveRequestDto, UserDetails principal) {
        // 존재하는 게시판인지 먼저 확인, 댓글 작성하려는 찰나에 게시글이 삭제될 수 있으므로
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판"));

        Reply reply = new Reply();
        reply.setContent(replySaveRequestDto.getContent());
        reply.setBoard(board);
        reply.setMember(memberRepository.findById(principal.getUsername()).get());
        return replyRepository.save(reply).getId();
    }

    // Read, 게시글 내에 존재하는 모든 댓글 불러오기
    public List<ReplyResponseDto> findReplyOnPost(Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        List<Reply> replyList = board.getReplyList();
        List<ReplyResponseDto> replyResponseDtos = new ArrayList<>();

        for (Reply reply : replyList)
        {
            ReplyResponseDto dto = new ReplyResponseDto();
            dto.setContent(reply.getContent());
            dto.setWriter(reply.getMember().getId());
            dto.setReg_dt(reply.getRegDt());

            replyResponseDtos.add(dto);
        }
        return replyResponseDtos;
    }

    // 댓글 id 컬럼으로 불러오기
    public ReplyResponseDto findById(Long replyId) {

        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 댓글"));

        ReplyResponseDto replyResponseDto = new ReplyResponseDto();

        replyResponseDto.setId(reply.getId());
        replyResponseDto.setWriter(reply.getMember().getId());
        replyResponseDto.setContent(reply.getContent());
        replyResponseDto.setReg_dt(reply.getRegDt());

        return replyResponseDto;
    }

    // Update
    public Long updateReply(Long replyId, ReplySaveRequestDto replySaveRequestDto) {
        Reply reply = replyRepository
                .findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글"));

        reply.setContent(replySaveRequestDto.getContent());

        return replyRepository.save(reply).getId();
    }

    // Delete
    public void deleteReply(Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 댓글"));

        replyRepository.delete(reply);
    }


}
