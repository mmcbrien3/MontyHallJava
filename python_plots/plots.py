import matplotlib.pyplot as plt
import csv
import sys
import os
import pandas

rand_strat_theory = []
stay_strat_theory = []
switch_strat_theory = []

n = list(range(3,101))
for i in n:
    rand_strat_theory.append(1/(i-1))
    stay_strat_theory.append(1/i)
    switch_strat_theory.append((i-1)/(i*(i-2)))

fig, (ax1, ax2) = plt.subplots(2)
fig.suptitle('Effectiveness of Strategies by Number of Doors in Game')

ax1.plot(n, switch_strat_theory, label='Switch')
ax1.plot(n, rand_strat_theory, label='Random')
ax1.plot(n, stay_strat_theory, label='Stay')
ax1.legend()
ax1.set_title('Theoretical Effectiveness')
ax1.set_xlabel('Number of Doors')
ax1.set_ylabel('Win Rate (%)')

data_df = pandas.read_csv(os.path.join(os.getcwd(), 'data', 'test_results.csv'), index_col=False)

ax2.plot(n, data_df['SwitchStrategy'], label='Switch')
ax2.plot(n, data_df['RandomStrategy'], label='Random')
ax2.plot(n, data_df['StayStrategy'], label='Stay')

ax2.legend()
ax2.set_title('Empirical Effectiveness')

ax2.set_xlabel('Number of Doors')
ax2.set_ylabel('Win Rate (%)')
plt.show()
