/*
Annotation

코드사이에 주석처럼 쓰이며 특별한 의미/기능을 수행하도록 하는 기술. 즉, 프로그램에게 추가적인 정보를 제공해주는 메타데이터

- 용도 -
1. 컴파일러에게 코드작성 문법에러 체크하도록 정보제공
2. 소프트웨어 개발 툴이 빌드나 배치 시 코드를 자동 생성하도록 정보 제공
3. 런타임시 특정 기능 실행하도록 정보 제공

- 어노테이션 사용 순서 -
1. 어노테이션 정의
2. 클래스에 어노테이션 배치
3. 코드 실행 중 Reflection을 이용해 추가정보를 획득하여 기능 실시

- 종류 -
1. Bean으로 등록
	@Component : 직접작성한 Class를 Bean으로 등록(1개 class 단위) value로 Bean이름 지정
	@Configuration : 1개 이상의 bean등록 시 설정. @Bean을 해당 Class의 method에 적용하면 Autowired로 Bean을 부를 수 있음.
	@Bean : 개발자가 직접 제어가 불가한 외부라이브러리에서 생성한 객체 등록
	@Autowired : 속성(field), setter method, constructor(생성자)에서 사용. Type에 따라 알아서 Bean을 주입.
				무조건적인 객체에 대한 의존성을 주입시킴. 스프링이 자동적으로 값을 할당.
2. Controller
	@Controller : Spring의 Controller를 의미. Spring MVC에서 Controller클래스에 사용됨
	@RestController : Springd의 Controller 중 View로 응답하지 않는 것. method의 반환결과를 JSON형태로 반환.
	
	*둘의 차이
	 Controller : API와 view동시 사용하는 경우. view return이 주목적
	 RestController : view필요없는 API만 지원하는 서비스에서 사용. data(json,xml) return이 주목적

@Service : Service Class에서 사용됨. 비즈니스 로직을 수행하는 Class라는 것을 나타냄
@Repository : DAO Class에서 사용됨. DataBase에 접근하는 method를 가진 Class에서 사용.

3. Mapping
	@RequestMapping : 요청 URL을 어떤 method가 처리할지 mapping.
					요청받는 형식인 get, post, patch, put, delete를 정의하기도 함.(안하면 자동적으로 get으로 설정됨)
					
	@GetMapping : RequestMapping(Method=RequestMethod.GET)과 같음
	@PostMapping : RequestMapping(Method=RequestMethod.POST)과 같음
	@PutMapping : RequestMapping(Method=RequestMethod.PUT)과 같음
	@Patch : RequestMapping(Method=RequestMethod.PATCH)과 같음
	@DeleteMapping : RequestMapping(Method=RequestMethod.DELETE)과 같음
	
4. Lombok : 반복메서드 작성코드를 줄여주는 라이브러리

5. JPA : 실제쿼리 사요엉ㅄ이 Entity클래스의 수정을 통해 작업함. SQL이 아닌 객체중심으로 개발
	@Entity : 실제 DB의 테이블과 매칭될 Class임을 명시.
*/
