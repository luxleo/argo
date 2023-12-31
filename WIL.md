# WIL 프로젝트 하며 배운것
## spring data jpa
- 1.엔티티의 enum 타입의 필드 업데이트 시 => Status status = Status.valueOf(status)
    로 업데이트한다
- 2.dto로 내보낼때 enum type -> string
  Converter<Status, String> enumToStringConverter = ctx -> ctx.getSource().toString();
  modelMapper.addConverter(enumToStringConverter);

        // Map the entity to the DTO
        ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
## queryDSL
```text
        //LEARN: projection절의 조회할 필드도 최소한 으로 하자.
        JPAQuery<Long> preCnt = query.select(question.id.count())
                .from(question)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        //LEARN: fetchCount가 deprecated 되었다. 아래와 같이 작성하자
        return PageableExecutionUtils.getPage(content, pageable, ()->preCnt.fetch().size());
```
## owner-slave entity클래스 일때 삭제도 owner클래스에서
```text
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Slave> slaves = new ArrayList<>();
    
    // Other fields, getters, setters, etc.
}
@Entity
public class Slave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    
    // Other fields, getters, setters, etc.
}
@Service
public class OwnerService {
    
    @Autowired
    private OwnerRepository ownerRepository; // Autowire the repository
    
    @Transactional
    public void deleteSlaveFromOwner(Long ownerId, Long slaveId) {
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(EntityNotFoundException::new);
        
        // Find the slave entity to delete
        Slave slaveToRemove = owner.getSlaves().stream()
                .filter(slave -> slave.getId().equals(slaveId))
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
        
        // Remove the slave from the owner's collection
        owner.getSlaves().remove(slaveToRemove);
        
        // The orphanRemoval attribute will automatically remove the slave from the database
    }
}

```
- 1.위와 같이 owner클래스에서 slave 클래스를 삭제 할 수있다. 뭐가 더 경제적인지 로그로 살펴보자

## 오류발견:
- 1.beforeEach로 서로 다른 클래스에 있더라도 한 꺼번에 돌리니까 공통된 엔티티가 생성되는 모양이다.
- 2.만일 제대로 동작하지 않을시에 캐시를 고려하여 삭제해주자!!
- 3.HttpServlet의 session은 Map으로 관리된다. => 삭제는 session.invalidate() ,자동 삭제 : 세션 유효시간이 지나면 자동삭제