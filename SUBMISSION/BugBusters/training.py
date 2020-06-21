import pandas as pd
import numpy as np
from sklearn.linear_model import LogisticRegression
import pickle

def data_split(data,ratio):
    np.random.seed(42)
    shuffled = np.random.permutation(len(data))
    test_set_size = int(len(data) * ratio)
    test_indices = shuffled[:test_set_size]
    train_indices = shuffled[test_set_size:]
    return data.iloc[train_indices], data.iloc[test_indices]


if __name__ == "__main__":
        df = pd.read_csv('Data.csv')
        # train, test = data_split(df, 0.2)
        X_train = df[['fever', 'BodyPain', 'age', 'RunnyNose', 'diffBreath']].values
        # X_test =test[['fever', 'BodyPain', 'age', 'RunnyNose', 'diffBreath']].to_numpy()
        Y_train = df['infectionProb'].values
        # Y_test = test[['infectionProb']].to_numpy().reshape(599,)

        clf = LogisticRegression(solver='lbfgs')
        clf.fit(X_train, Y_train)

        # open a file, where you ant to store the data
        file = open('model.pkl', 'wb')

        # dump information to that file
        pickle.dump(clf, file)


        


