package org.ixkit.anvilj.member.service.impl;

import org.ixkit.anvilj.member.entity.Member;
import org.ixkit.anvilj.member.mapper.MemberMapper;
import org.ixkit.anvilj.member.service.IMemberService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 基础资源 成员表
 * @Author: jeecg-boot
 * @Date:   2024-01-02
 * @Version: V1.0
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}
