# APP FEATURE

1. Login, logout, Regist
2. Manage desk
    - view all desk
    - add desk
    - modify desk
    - delete desk
    - order
    - pay
3. Manage menu
    - view all category, dish
    - add category, dish
    - modify category, dish
    - delete category, dish
4. Manage staff
    - view all staff
    - add staff
    - modify staff
    - delete staff
5. Analys
    - view
6. Setting

# Structure
    * Staff:
        - id
        - username
        - password
        - fullname
        - sex
        - iden
        - number
        - avatar
        - role id
    * Desk
        - id
        - name
        - status
    * Dish
        - id
        - name
        - cat
        - price
        - img
    * Category
        - id
        - name
    * Order
        - id
        - desk
        - staff
        - status
        - date
    * OrderDetail
        - order
        - dish
        - amount
    * Role
        - id
        - name
