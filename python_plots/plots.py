import matplotlib.pyplot as plt
import matplotlib as mpl
import os
import pandas


FIG_COLOR = '#44475a'
TEXT_COLOR = '#f8f8f2'
PLOT_COLOR = '#282a36'
plt.rcParams['axes.facecolor'] = PLOT_COLOR


mpl.rcParams['text.color'] = TEXT_COLOR
mpl.rcParams['axes.labelcolor'] = TEXT_COLOR
mpl.rcParams['xtick.color'] = TEXT_COLOR
mpl.rcParams['ytick.color'] = TEXT_COLOR

rand_strat_theory = []
stay_strat_theory = []
switch_strat_theory = []

n = list(range(3,101))
for i in n:
    rand_strat_theory.append(1/(i-1))
    stay_strat_theory.append(1/i)
    switch_strat_theory.append((i-1)/(i*(i-2)))

fig, (ax1, ax2) = plt.subplots(2)
fig.patch.set_facecolor(FIG_COLOR)
fig.suptitle('Effectiveness of Strategies by Number of Doors in Game')

ax1.plot(n, switch_strat_theory, label='Switch', color='#50fa7b')
ax1.plot(n, rand_strat_theory, label='Random', color='#ffb86c')
ax1.plot(n, stay_strat_theory, label='Stay', color='#ff79c6')
ax1.legend()
ax1.set_title('Theoretical Effectiveness')
ax1.set_xlabel('Number of Doors')
ax1.set_ylabel('Win Rate (%)')

data_df = pandas.read_csv(os.path.join(os.getcwd(), 'data', 'test_results.csv'), index_col=False)

ax2.plot(n, data_df['SwitchStrategy'], label='Switch', color='#50fa7b')
ax2.plot(n, data_df['RandomStrategy'], label='Random', color='#ffb86c')
ax2.plot(n, data_df['StayStrategy'], label='Stay', color='#ff79c6')

ax2.legend()
ax2.set_title('Empirical Effectiveness')

ax2.set_xlabel('Number of Doors')
ax2.set_ylabel('Win Rate (%)')

outpath = os.path.join(os.getcwd(), 'data', 'effectiveness.png')
fig = mpl.pyplot.gcf()
fig.set_size_inches(16, 9.9)
fig.savefig(outpath, dpi=200)
