## Setting Up the Development Server

## File Structure/Tree

- **Covid.ipynb** is a python notebook where I have implemented the training of model to predict probability.
- To make the model integrate with flask server I have created a **training.py** in which where I have written the training commands of the model which are written in jupyter
  notebook.
- A **main.py** file reponsible for creating a flask server which is taken from flask minimal template site.
- A **index.html** file is the file created using bootstrap stater template which is having a bootstrap navbar,form feature.
- A **show.html** is a file having same html code as index.html have used to show the results after form submission.

## Quick Start

- Install the Pacakges:

  `pip install numpy pandas sklearn flask`

- Run the training.py file for training our Regression model to predict probalities and this will generate pickle file to save your model training each time when you update your     training model dataset.

  1. Run : `python training.py` 
  
  
- Run the main.py to setup the flask server:-
 
  2. Run: `python main.py`
  
  
  Your Server is running live at http://127.0.0.1:5000/
