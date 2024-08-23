Development of hangman game, using the following frameworks:
<ul>
    <li>Servlets + Arrays (data);</li>
    <li>Servlets + DB connection</li>
    <li>Servlets + Hibernate</li>
    <li>Struts + Hibernate</li>
    <li>Spring + Hibernate</li>
</ul>

At the end of implementing the hangman game in each of the frameworks, make a brief presentation of that framework.

The database should have 4 tables, namely:
<ul>
    <li>Word</li>
    <li>Category</li>
    <li>Category/Word</li>
    <li>Difficulty</li>
</ul>

Before the game starts, the user should choose the difficulty and the category. If the user does not choose any of the options, the categories and the difficulty should be random.
This image was taken from http://www.thegamegal.com/ and modified for this application which was inspired by https://github.com/sonyseng/hangman/

- Run docker ```docker-compose up -d``` to create mysql 5.7 database container
- Tomcat 8.0 by placing the project there, another configuration to make is this selection in Tomcat:
<img src="https://github.com/walyson-scarazzati/OqueSpringMVCDevmedia/assets/53382989/4c4a08db-8ddc-4cce-a7f0-c09670235fb9" alt="Descrição da Imagem" width="600" height="400" />

- Access http://localhost:9090/jogodaforca/ and choose Difficulty
![image](https://github.com/user-attachments/assets/76494e0c-3a42-48e6-b010-64887932075c)

- Choose Category
![image](https://github.com/user-attachments/assets/13b3da05-96bb-479f-96f5-af3875582b8d)

- Click in começar jogo to start game
  
![image](https://github.com/user-attachments/assets/a9c7928d-6891-48b2-bb15-d20a59d5e3aa)

- Type the letter to find the word
![image](https://github.com/user-attachments/assets/1a2c59a5-4c87-44a4-8573-a16ceca4a413)


