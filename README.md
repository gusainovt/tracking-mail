# Tracking Mail
## Описание проекта
Tracking Mail — это приложение, предназначенное для регистрации почтовых отправлений и отслеживания их передвижения между почтовыми отделениями. 
Оно позволяет пользователям получать информацию о текущем статусе и полной истории передвижения конкретного почтового отправления.
## Технологии в проекте
- **Язык и окружение:** Java 17, Spring Boot, Hibernate
- **База данных**: PostgreSQL
- **Управление миграциями**: Liquibase
- **Контейнеризация**: Docker
## Установка и запуск
1. Клонируйте репозиторий:
```
git clone https://github.com/gusainovt/tracking-mail.git
```

2. Запустите приложение с помощью Docker.
3. Откройте Swagger UI для доступа к API:
```
http://localhost:8080/swagger-ui.html
```
## Примеры запросов
### 1. Регистрация почтового отправления
Запрос:
`POST /postal-items`
```
{
    "recipientName": "Name postal office",
    "type": "PACKAGE",
    "recipientIndex": "123456",
    "recipientAddress": "Lenina, 1",
    "index": "654321",
    "name": "Main postal office",
    "address": "Pushkina, 2"
}
```
### 2. Прибытие почтового отправления в почтовое отделение
Запрос: 
`PATCH /postal-items/arrive/{postalItemId}`
```
{
    "index": "654321",
    "name": "Main postal office",
    "address": "Pushkina, 2"
}
```
### 3. Убытие почтового отправления из почтового отделения
Запрос:
`PATCH /postal-items/departure/{postalItemId}`
### 4. Получение почтового отправления адресатом
Запрос:
`PATCH /postal-items/receive/{postalItemId}`
### 5. Получение истории передвижения почтового отправления
Запрос:
`GET /postal-items/statuses/movement-history/{postalItemId}`
### 6. Получение текущего статуса почтового отправления
Запрос:
`GET /postal-items/statuses/{postalItemId}`
