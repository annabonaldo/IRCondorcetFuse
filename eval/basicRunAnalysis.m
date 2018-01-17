load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicRunData.mat');

[measuredRunSet, poolStats, runSetStats, inputParams] = averagePrecision(pool, basicRunSet);

x = mean(measuredRunSet{:, 1:end});

meanAP = array2table(x);
meanAP.Properties.VariableNames = measuredRunSet.Properties.VariableNames;
meanAP.Properties.RowNames = {'meanAP'};

measuredRunSet = [measuredRunSet;meanAP];

plot(351:400, measuredRunSet{1:50, 1:end});
title('Precision for the different retrieval methods in different topics');
ylabel('precision')
xlabel('topics')
legend(measuredRunSet.Properties.VariableNames);

%Stampare la Mean average precision come tabella
%measuredRunSet{51, 1:end}
%mean = mean(measuredRunSet{51, 1:end});
%mean;

clear;