# 

# AuthenticationProvider

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1a3e4cab-5e0f-43f5-96fe-7688c98a4828/Untitled.png)

- interface
- 2개의 메소드 제공
    1. authenticate(authentication)
        - 전달받은 파라미터에는 사용자가 입력한 ID/PW가 존재
        - 자기를 위임한 필터로부터 받은 인증객체를 전달
        1. ID 검증
            - UserDetailsService 에서 사용자 정보 조회 → 없다면 UserNotFoundException
            - 만약에 있다면 User 를 UserDetails로 타입 변화를 하여 전달해줌
        2. password 검증
            - UserDetails에 저장된 Pw와 전달받은 authentication에 저장된 PW와 비교
            - 일반적으로 암호화된 PW를 저장하기 때문에 matches를 이용하여 검증 → 없다면 BadCredentialException 발생
        3. 추가 검증
            - 최종적으로 Authentication객체에 (user, authorities) 반환
            - 인증객체를 AuthenticationManager에게 전달
    2. supports
        - 인증을 처리할수있는 요건 충족이 되는 지 검증
