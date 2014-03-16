load SS.txt;
load TD.txt;
load AKS.txt;
load MR.txt;
load WS.txt;

figure(1)
semilogx(SS(:,2),SS(:,4), 'ok')
hold on
semilogx(AKS(:,2),AKS(:,4)/100, 'xk')
semilogx(MR(:,2),MR(:,4), '+k')
semilogx(TD(:,2),TD(:,4), 'sk')
semilogx(WS(:,2),WS(:,4), 'dk')
hold off
xlabel('n')
ylabel('time')
legend('SS','AKS/100','MR','TD','WS')
title('Execution time for 10,000 trials for composite numbers')

figure(2)
loglog(SS(:,2),1-SS(:,6), 'ok')
hold on
loglog(AKS(:,2),1-AKS(:,6), 'xk')
loglog(MR(:,2),1-MR(:,6), '+k')
loglog(TD(:,2),1-TD(:,6), 'sk')
loglog(WS(:,2),1-WS(:,6), 'dk')
hold off
xlabel('n')
ylabel('error')
legend('SS','AKS','MR','TD','WS')
title('Error for 10,000 trials for composite numbers')