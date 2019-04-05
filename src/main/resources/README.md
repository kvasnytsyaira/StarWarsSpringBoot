####Application shows Star Wars characters  compared to the inputted character. One character from the same race and one from different race.
#### /create/{character}/{name} -- POST. Compare randomly generated characters .
        character -character`s race
        name - character`s name 
        
        request example - /people/Luke Skywalker
        
        response example 
        {
            "uuid": "88eeedd8-e89a-4881-9cef-0e421be9c2d4"
        }
#### /get/{uuid} -- GET. Will return you a result of calculation that was done
        uuid - result`s UUID
        
        request example - /get/88eeedd8-e89a-4881-9cef-0e421be9c2d4
        {
            "response": "The Luke Skywalker is human , as Saesee Tiin and not mammal as, Sullustan"
        }
####/getAll/{character} -- GET. Return a list of all characters. 2 character per page
       character -character`s race
        request example - /getAll/people/
        {
            "url": "http://localhost:8080/getAll/people?page=1",
            "list": [ ]
        }