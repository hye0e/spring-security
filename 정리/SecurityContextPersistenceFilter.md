### SecurityContextPersistenceFilter
- SecurityContext 객체의 생성, 저장, 조회
- 익명 사용자
  - **새로운 SecurityContext객체를 생성**하여 SecurityContextHolder에 저장
  - AnonymousAuthenticationFilter에서 AnonymousAuthenticationToken객체를 Security에 저장

- 인증 시
  - **새로운 SecurityContext 객체를 생성**하여 SecurityContextHolder에 저장
  - UsernamePasswordAuthenticationFilter에서 인증 성공 후 SecurityContext에 UsernamePasswordAuthentication객체를 SecurityContext에 저장
  - 인증이 최종 완료되면 Session에 SecurityContext를 저장

- 인증 후
  - **Session에서 SecurityContext 꺼내어** SecurityContextHolder에서 저장
  - SecurityContext 안에 Authentication 객체가 존재하면 계속 인증을 유지한다

- 최종 응답 시 공통
  - SecurityContextHolder.clearContext()

- 과정
  1. Request
  2. SecurityContextPersistenceFilter 매 요청마다 받음
  3. SecurityContextPersistenceFilter 내부에 사용자 정보를 조회할 수 있는 HttpSecurityContextRepository 가 존재 
  4. 인증
     - 인증 전
       - 새로운 컨텍스트 생성(SecurityContextHolder) -> SecurityContext = null;
       - AuthFilter 거침
       - 인증 후 인증객체 저장(SecurityContext에 Authentication객체 저장)
       - Save SecurityContext
       - SecurityContextHolder에서 SecurityContext 제거
       - client 에 Response
     - 인증 후
       - Session에 저장되어 있는 SecurityContext를 Load
       - SecurityContext에 Authentication객체 저장
       - chain.doFilter
       - 따라서, 인증을 받은 이후에는 따로 검증을 받지 않음

- SecurityContextPersistenceFilter 역할
  1. create Security
     - SecurityContextHolder > TreadLocal > SecurityContext(**null**)
  2. load from session
     - SecurityContextHolder > TreadLocal > SecurityContext(**Authentication 정보 있음**)
