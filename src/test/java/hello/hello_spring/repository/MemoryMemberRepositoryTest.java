package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    //테스트할 대상 객체 하나 만드는것, 즉 저장소 객체가 생기고
    //private static Map<Long, Member> store = new HashMap<>();   // 비어 있음
    //private static long sequence = 0L;                           // 0 가 준비된다
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {

    }

    @Test
    public void save() {
        Member member1  = new Member();
        member1.setName("spring");

        repository.save(member1);

        Member result = repository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(result);
    }


    //그다음은 파인드 바이 네임 테스트!!
    @Test
    public void findByName() {
        Member member1 = new Member();//회원 객체르르 만듦
        member1.setName("spring1"); //이름을 스프링1로 저장함
        repository.save(member1); //저장소 객체 안의 store(Map)에 들어감

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    //여기서는 저장한 회원 전부가 다 들어있나 확인
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //실행: 전부 꺼내기
        List<Member> result = repository.findAll();

        //검증: 2명이 맞나
        assertThat(result.size()).isEqualTo(2);

    }

}

