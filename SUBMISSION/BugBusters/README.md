# COVID-19 Predictor

A simple web application that predicts the chances for a person's being infected by COVID-19 based on symptoms.

[Preview on Heroku](http://covid-predict-app.herokuapp.com)

## Technologies used

- HTML, CSS, and JavaScript for the public webpages.
- Bootstrap and jQuery for enhancing the front-end.
- Flask for the back-end server and machine learning model handling in Python.
- SQL for storing the logs in an SQLite3 database.
- NewsAPI (http://newsapi.org) for fetching relevant news.

# Setting Up the Application Server

## File Structure/Tree

- **apikey.py** : Contains the API Key, must be modified before deployment.
- **app.py** : The main Flask application.
- **covid.ipynb** : A Jupyter notebook with a detailed procedure of model training.
- **Data.csv** : The training data for the model. If absent, execute _dummydata.py_ to generate dummy data.
- **dummydata.py** : Generates a reliable dummy data CSV file for training the model.
- **main.db** : A SQLite database for storing the query logs.
- **model.pkl** : The trained model as generated from _Data.csv_ by using _scikit-learn_. If absent, _training.py_ must be executed in the presence of _Data.csv_.
- **Procfile** : Information for deployment on Heroku.
- **README.md** : The readme file, with information about the project.
- **requirements.txt** : An extended list of the PyPI packages required and/or might be needed in the project. It is formatted for deployment on Heroku. If absent, execute _pip freeze>requirements.txt_ while within the development environment having the requirements pre-installed.
- **training.py** : Generates _model.pkl_ by training a model using _Data.csv_.

## Quick Start

- Install the Pacakges:

  `pip install -r requirements.txt`

- Add the NewsAPI API Key to _apikey.py_. You can obtain one from http://newsapi.org.

  _apikey.py_:

  ```
  apikey = 'YOUR_API_KEY_HERE'
  ```

  This isn't a requirement unless you wish to display recent news.

- Acquire the data in the form of a CSV file named _Data.csv_. Else, execute _dummydata.py_ to generate dummy data.

  `python dummydata.py`

  _Data.csv_:

  ```
  fever,BodyPain,age,RunnyNose,diffBreath,infectionProb
  99.6,0,95,0,1,0
  102.1,0,3,1,1,0
  ...
  ```

  |   Parameter   |                                              Value                                              |
  | :-----------: | :---------------------------------------------------------------------------------------------: |
  |     fever     |                                     Body temperature in °F                                      |
  |   BodyPain    |                               Body pain: 0 for none, 1 for severe                               |
  |      age      |                                        The person's age                                         |
  |   RunnyNose   |                 Whether or not the person has a runny nose: 0 for no, 1 for yes                 |
  |  diffBreath   | Whether or not the person faces difficulty in breathing: 0 for none, 1 for little, 2 for severe |
  | infectionProb |            Whether or not the person is infected: 0 for not infected, 1 for infected            |

- Get the model trained by executing _training.py_.

  `python training.py`

- Host the Flask server by executing `flask run`.

  `flask run`

  By default, the server will be live at http://localhost:5000/ in debug mode. Other settings can be modified as per requirement.

# Credits

Authors (BugBusters):

- [Param Siddharth](https://www.paramsid.com/)
- [Ritesh Yadav](https://github.com/DARK-art108)
- [Harsh Marolia](https://github.com/HarshMarolia)
- Sushant Sharma
- [Paridhi Malav](https://github.com/paridhi1314)

Favicon obtained from https://findicons.com.  
News API from http://newsapi.org.

---

Made with ❤️ by BugBusters
