package com.algo.inc.web.service;

import com.algo.inc.domain.reply.Reply;
import com.algo.inc.web.dto.board.BoardResponseDto;
import com.algo.inc.web.dto.reply.ReplyResponseDto;
import com.algo.inc.web.dto.reply.ReplySaveRequestDto;
import com.algo.inc.web.repository.BoardRepository;
import com.algo.inc.web.repository.MemberRepository;
import com.algo.inc.web.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // Create
    public Long registerReply(Long boardId, ReplySaveRequestDto replySaveRequestDto) {
        Reply reply = new Reply();
        reply.setContent(replySaveRequestDto.getContent());

        reply.setMember(memberRepository.findById("이효리")
                .orElseThrow(()->new IllegalArgumentException("유저 오류")));
        reply.setBoard(boardRepository.findById(boardId)
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 게시판")));

        return replyRepository.save(reply).getId();
    }

    // Read, 게시글 내에 존재하는 모든 댓글 불러오기
    public List<ReplyResponseDto> findReplyOnPost(Long boardId) {
        List<Reply> list = replyRepository.findAllByBoard_Id(boardId);
        List<ReplyResponseDto> replyList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            ReplyResponseDto replyResponseDto = new ReplyResponseDto();
            replyResponseDto.setId(list.get(i).getId());
            replyResponseDto.setWriter(list.get(i).getMember().getId());
            replyResponseDto.setContent(list.get(i).getContent());
            replyResponseDto.setReg_dt(list.get(i).getRegDt());
            replyList.add(replyResponseDto);
        }
        if(replyList.isEmpty())
            return null;
        return replyList;
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
