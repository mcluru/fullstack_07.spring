/*
application.properties

런타임 시 다양한 환경에서 동작할 수 있도록 필요한 옵션 제공. ex) 메일서버 구축, db종류별 설정, 로그사용여부 설정 등
개발환경, 운영환경, 실제 서비스 등 다양한 환경에 따른 설정 옵션 적용 가능. 환경에 따른 설정값을 프로퍼티 파일을 통해
코드변경 없이 바꿀 수 있음.
application.properties : 환경마다 변경가능한 값 설정
constant in class : 환경에 따라 바뀌지 않는 값

https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
위 링크에서 application.properties에서 사용가능한 디폴트 옵션 확인 가능

스프링부트는 application.properties 파일을 디폴트로 사용.
추가로 운영(dev.properties), 서비스(prod.properties)파일 생성해 적용 가능
 */

server.port=8090


# DataSource Setting
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3306/spring
spring.datasource.username=root
spring.datasource.password=12345


# JPA Setting
spring.jpa.hibernate.ddl-auto=none	// 데이터베이스 스키마 생성/수정 동작 지정.
	// create : 기존테이블 삭제 후 다시 생성 (drop+create)
	// create-drop : create와 같으나 종료시점에 테이블 drop
	// update : 기존 스키마 유지하며 JPA에 의한 변경분만 반영(운영DB에서는 사용하면 안됨)
	// validate : 엔티티와 테이블이 정상 매핑되었는지 확인
	// none : 사용하지 않음 (사실상 없는 값)
		// 개발초기단계는 create/update, 테스트서버는 update/validate, 운영서버는 validate/none
spring.jpa.generate-ddl=false		// 위의 속성 사용할지 말지 결정하는 옵션 (기본값 false)
spring.jpa.show-sql=true			// JPA가 생성한 SQL문을 보여줌
spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect	//mariadb 버전
spring.jpa.properties.hibernate.format_sql=true	// 출력되는 SQL문 읽기 쉽게 포맷팅

/*
Spring Data JPA : JPA를 더 편리하게 사용하기 위한 모듈
	- hibernate와 같은 구현체들을 좀 더 쉽게 사용가능하도록 추상화함.
	- JPA를 한단계 추상화시킨 Repository 인터페이스를 제공
	- 인터페이스에 정해진 규칙대로 메소드 입력 -> Spring이 알아서 메소드 이름에 적합한 쿼리를 날릴
		구현체를 만들어 Bean으로 등록
JPA : 자바 어플리케이션에서 관계형 DB를 사용하는 방식을 정의한 인터페이스
	  즉, ORM기술에 대한 표준기술 명세로 구현이 x.(특정 기능을 하는 라이브러리가 x)
hibernate : JPA라는 명세의 구현체의 한 종류. 인터페이스를 직접 구현한 라이브러리.

JPA를 사용하기 위해 반드시 hibernate를 사용할 필요는 x(필요에 따라 다른 구현체 사용 가능)
hibernate보다 Spring Datd JPA사용이 권장되는 이유
	- 구현체/저장소 교체의 용이성
*/


# Logging Setting
logging.level.org.hibernate=info	// 로그 설정
	// trace : 가장 상세한 로그. 가장 많은 정보
	// debug : 디버깅 정보
	// info : 중요한 정보 기록
	// warn : 경고메세지
	// error : 오류/예외 기록

# Thymeleaf Cache Setting
spring.thymeleaf.cache=false	// false : 재시작 없이 새로고침만으로 타임리프 수정 반영

# file upload / download setting
path.upload=d:/temp/upload/
path.download=d:/temp/download/
