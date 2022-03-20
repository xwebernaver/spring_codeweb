package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyPageDTO;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
//@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService {
			
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;	
		
//	public void setMapper(ReplyMapper mapper) {
//		this.mapper = mapper;
//	}		
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	
	// 트랜잭션 (댓글 등록 + 게시글 댓글개수 업데이트)
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("Reply register....." + vo);
		
		boardMapper.updateReplyCnt(vo.getBno(), 1);		// 게시글 댓글 수 등록 
				
		log.info("vo.getBno() : " + vo.getBno());
		
		return mapper.insert(vo);
	}
	
	@Override
	public ReplyVO get(Long rno) {
		log.info("get....." + rno);
		return mapper.read(rno);
	}
	
	@Override
	public int modify(ReplyVO vo) {
		log.info("modify....." + vo);
		return mapper.update(vo);
	}
	
	
	// 트랜잭션 (댓글 삭제 + 게시글 댓글개수 업데이트)
	@Transactional
	@Override
	public int remove(Long rno) {
		log.info("remove....." + rno);
		
		ReplyVO vo = mapper.read(rno);
		
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		
		return mapper.delete(rno);
	}
	
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList....." + cri + " # bno:" + bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long bno) {
		return new ReplyPageDTO(
				mapper.getCountByBno(bno),
				mapper.getListWithPaging(cri, bno));
	}
	
}
