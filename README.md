# API 명세서

| **HTTP Method** | **Endpoint**         | **Description**             | **Path Variable / Body**                                            | **Response**                                     | **Status Code**    |
|------------------|----------------------|-----------------------------|---------------------------------------------------------------------|--------------------------------------------------|--------------------|
| POST             | `/schedule`             | 일정 생성                   | **Body**: `ScheduleRequestDto`                                    | `ScheduleResponseDto`                            | 201 Created        |
| GET              | `/schedule`             | 전체 일정 조회              | -                                                                 | `List<ScheduleResponseDto>`                      | 200 OK             |
| PUT              | `/schedule/{id}`        | 선택 일정 수정              | **Path Variable**: `id` (Long)<br>**Body**: `ScheduleRequestDto`  | `ScheduleResponseDto`                            | 200 OK             |
| DELETE           | `/schedule/{id}`        | 선택 일정 삭제              | **Path Variable**: `id` (Long)                                    | -                                                | 200 OK             |

---

## Request Param & Body (예시)

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
