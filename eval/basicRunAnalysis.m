addpath(genpath('~/Desktop/matters'), '-end')

load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData_10.mat');

[measuredRunSet, poolStats, runSetStats, inputParams] = averagePrecision(pool, basicRunSet);

x = mean(measuredRunSet{:, 1:end});

meanRunAP = array2table(x);
meanRunAP.Properties.VariableNames = measuredRunSet.Properties.VariableNames;
meanRunAP.Properties.RowNames = {'meanAP'};

measuredRunSet = [measuredRunSet;meanRunAP];

plot(351:400, measuredRunSet{1:50, 1:end});
title('Precision for the different retrieval methods in different topics');
ylabel('precision')
xlabel('topics')
legend(measuredRunSet.Properties.VariableNames);

%Per stampare la Mean average precision come tabella usa meanAP

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData_10.mat', ...
    'meanRunAP', 'measuredRunSet', '-append');

clear;