### Authentication 인터페이스
- 사용자의 인증 정보를 저장하는 **토큰개념**
- 인증 시 id와 password를 담고 인증 검증을 위해 전달되어 사용된다.
- 인증 후 최종 결과(user 객체, 권한 정보)를 담고, SecurityContext에 저장되어 전역적으로 참조가 가능하다
    ```
  Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
  ```
- 구조
  - pricipal: 사용자 아이디 or User 객체를 저장
  - credentials: 사용자 비밀번호
  - authorities: 인증된 사용자의 권한 목록
  - details: 인증 부가 정보
  - Authenticated: 인증 여부

- 과정
  1. Request Login: username + password 를 전달
  2. UsernamePasswordAuthenticationFilter 를 거침
  3. Authentication 객체 생성 _(Authenticated: FALSE)_
  4. AuthenticationManager가 Authentication를 가지고 인증 처리
  5. 인증이 성공하게 되면, Authentication _(Authenticated: TRUE)_ 최종 인증 결과를 저장
  6. SecurityContextHolder SecurityContext 에 인증 객체 저장하여 전역적으로 사용할 수 있게 된다.

