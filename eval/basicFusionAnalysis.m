addpath(genpath('~/Desktop/matters'), '-end')

load('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData_10.mat');

[measuredFusionSet, poolStats, runSetStats, inputParams] = averagePrecision(pool, basicFusionSet);

x = mean(measuredFusionSet{:, 1:end});

meanFusionAP = array2table(x);
meanFusionAP.Properties.VariableNames = measuredFusionSet.Properties.VariableNames;
meanFusionAP.Properties.RowNames = {'meanAP'};

measuredFusionSet = [measuredFusionSet;meanFusionAP];

plot(351:400, measuredFusionSet{1:50, 1:end});
title('Precision for the different retrieval methods in different topics');
ylabel('precision')
xlabel('topics')
legend(measuredFusionSet.Properties.VariableNames);

%Stampare la Mean average precision come tabella usa meanFusionAP

save('~/Desktop/ProgettoIR/IRCondorcetFuse/eval/basicFusionData_10.mat', ...
    'meanFusionAP', 'measuredFusionSet', '-append');

clear;