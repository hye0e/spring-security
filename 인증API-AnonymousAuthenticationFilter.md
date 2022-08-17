### AnonymousAuthenticationFilter
- 익명사용자 인증 처리 필터
- 익명사용자와 인증 사용자를 구분해서 처리하기 위한 용도로 사용
<br>
(대신 AnonymousAuthenticationFilter 는 null로 판단하는 것이 아닌, AnonymousAuthentication Token인지로 판단한다.)
- 화면에서 인증 여부를 구현할 때 isAnonymous()와 isAuthenticated()로 구분해서 사용
- 인증객체를 세션에 저장하지 않는다.
