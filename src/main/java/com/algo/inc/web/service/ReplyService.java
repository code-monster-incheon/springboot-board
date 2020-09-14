package com.algo.inc.web.service;

import com.algo.inc.domain.board.Board;
import com.algo.inc.domain.reply.Reply;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.algo.inc.web.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long registerReply(Long boardId, ReplySaveRequestDto replySaveRequestDto) {
        // 존재하는 게시판인지 먼저 확인, 댓글 작성하려는 찰나에 게시글이 삭제될 수 있으므로
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판"));

        Reply reply = new Reply();
        reply.setContent(replySaveRequestDto.getContent());
        reply.setBoard(board);
        reply.setMember(memberRepository.findById(replySaveRequestDto.getUserId()).get());

        board.getReplyList().add(reply);

        return boardRepository.save(board).getId();
    }

    // Read, 게시글 내에 존재하는 모든 댓글 불러오기
    public List<ReplyResponseDto> findReplyOnPost(Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        List<Reply> replyList = board.getReplyList();
        List<ReplyResponseDto> replyResponseDtos = new ArrayList<>();

        for (Reply reply : replyList)
        {
            ReplyResponseDto dto = new ReplyResponseDto();
            dto.setId(reply.getId());
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

    @Transactional
    public Long updateReply(Long replyId, ReplySaveRequestDto replySaveRequestDto) {
        Reply reply = replyRepository
                .findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글"));

        reply.setContent(replySaveRequestDto.getContent());

        return replyRepository.save(reply).getId();
    }

    // Delete
    public List<ReplyResponseDto> deleteReply(Long boardId, Long replyId) {
        Reply reply = replyRepository.findById(replyId)
                .orElseThrow(()-> new IllegalArgumentException("댓글이 존재 하지 않습니다."));

        replyRepository.delete(reply);
        List<Reply> replyList = boardRepository.getReplyByBoardId(boardId);
        return replyList.stream()
                .map(rep -> ReplyResponseDto.builder()
                                .content(rep.getContent())
                                .writer(rep.getMember().getId())
                                .reg_dt(rep.getRegDt())
                                .id(rep.getId())
                                .build()
                )
                .collect(Collectors.toList());

    }


}
