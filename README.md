# ✅주의사항
충돌 없이 각 Lv을 실행하기 위해서는 약간의 조작이 필요합니다.<br>
아래에 Lv1을 원하는 레벨에 맞추어 수정 후 실행하시면 됩니다.
### ScheduleApplication<br>
@EntityScan(basePackages = "com.sparta.schedule.`Lv1`.entity")<br>
@ComponentScan(basePackages = {"com.sparta.schedule.`Lv1`"})<br>
@EnableJpaRepositories(basePackages = "com.sparta.schedule.`Lv1`.repository")<br>

# 🔐API 명세서(Schedule)

| **HTTP Method** | **Endpoint**         | **Description**             | **Path Variable / Body**                                            | **Response**                                     | **Status Code**    |
|------------------|----------------------|-----------------------------|---------------------------------------------------------------------|--------------------------------------------------|--------------------|
| POST             | `/schedule`             | 일정 생성                   | **Body**: `ScheduleRequestDto`                                    | `ScheduleResponseDto`                            | 201 Created        |
| GET              | `/schedule`             | 전체 일정 조회              | -                                                                 | `List<ScheduleResponseDto>`                      | 200 OK             |
| PUT              | `/schedule/{id}`        | 선택 일정 수정              | **Path Variable**: `id` (Long)<br>**Body**: `ScheduleRequestDto`  | `ScheduleResponseDto`                            | 200 OK             |
| DELETE           | `/schedule/{id}`        | 선택 일정 삭제              | **Path Variable**: `id` (Long)                                    | -                                                | 200 OK             |

# 🔐API 명세서(User)

| **HTTP Method** | **Endpoint**         | **Description**             | **Path Variable / Body**                                            | **Response**                                     | **Status Code**    |
|------------------|----------------------|-----------------------------|---------------------------------------------------------------------|--------------------------------------------------|--------------------|
| POST             | `/user`             | 사용자 생성                   | **Body**: `UserRequestDto`                                    | `UserResponseDto`                            | 201 Created        |
| GET              | `/user`             | 전체 사용자 조회              | -                                                                 | `List<UserResponseDto>`                      | 200 OK             |
| PUT              | `/user/{id}`        | 선택 사용자 수정              | **Path Variable**: `id` (Long)<br>**Body**: `UserRequestDto`  | `UserResponseDto`                            | 200 OK             |
| DELETE           | `/user/{id}`        | 선택 사용자 삭제              | **Path Variable**: `id` (Long)                                    | -                                                | 200 OK             |
---



## 📌Request Param & Body (예시)

- `POST /schedule` : 일정 생성
```param
/schedule
```
```json
{
    "username":"park",
    "title":"Mon todo",
    "content":"Do study"
}
```

- `GET /schedule` : 전체 일정 조회
```param
/schedule
```

- `PUT /schedule/{id}` : 선택 일정 수정
```param
/schedule/1
```
```json
{
    "username":"kim",
    "title":"updated title",
    "content":"updated content"
}
```

- `DELETE /todo/{id}` : 선택 일정 삭제
```param
/schedule/1
```
