from pylab import *
trials = 1000000

aks = np.loadtxt("AKS.txt") / 1000
mr =  np.loadtxt("MR.txt")  / trials
ss =  np.loadtxt("SS.txt")  / trials
td =  np.loadtxt("TD.txt")  / trials
ws =  np.loadtxt("WS.txt")  / trials

x = np.arange(31) + 1

plt.figure()
plt.title("Runtime")
plt.xlabel("log n")
plt.ylabel("ms")
plt.xlim([1, 31])
plt.ylim([0, .5e4])
plt.plot(x, ss[:, 0], '-o', label="SS")
plt.plot(x[:13], aks[:13, 0], '-x', label="AKS")
plt.plot(x, mr[:, 0], '-+', label="MR")
plt.plot(x, td[:, 0], '-s', label="TD")
plt.plot(x, ws[:, 0], '-^', label="WS")
plt.legend(loc="upper left")
plt.savefig("runtime.pdf")


plt.figure()
plt.title("Error")
plt.xlabel("log n")
plt.xlim([1, 31])
plt.plot(x, 1 - ss[:, 1], '-o', label="SS")
plt.plot(x[:13], 1 - aks[:13, 1], '-x', label="AKS")
plt.plot(x, 1 - mr[:, 1], '-+', label="MR")
plt.plot(x, 1 - td[:, 1], '-s', label="TD")
plt.plot(x, 1 - ws[:, 1], '-^', label="WS")
plt.legend(loc="upper right")
plt.savefig("performance.pdf")

def format(s):
    try:
        return str(round(s))
    except Exception:
        return s

def format2(s):
    try:
        return str(s)
    except Exception:
        return s

for t in range(31):
    if t < 13:
        print (" & ".join(map(lambda s: str(int(s)),[x[t], ss[t, 0], aks[t, 0], mr[t, 0], td[t, 0], ws[t, 0]])), "\\\\")
    else:
        #print (" & ".join(map(lambda s: str(int(s)) if "s" != "-" else s,[x[t], ss[t, 0], '-', mr[t, 0], td[t, 0], ws[t, 0]])))
        print(" & ".join(format(s) for s in [x[t], ss[t, 0], '-', mr[t, 0], td[t, 0], ws[t, 0]]), "\\\\")



for t in range(31):
    if t < 13:
        print (" & ".join(map(str,[x[t], ss[t, 1], aks[t, 1], mr[t, 1], td[t, 1], ws[t, 1]])), "\\\\")
    else:
        #print (" & ".join(map(lambda s: str(int(s)) if s != "-" else s,[x[t], ss[t, 1], '-', mr[t, 1], td[t, 1], ws[t, 1]])))
        print(" & ".join(str(s) for s in [x[t], ss[t, 1], '-', mr[t, 1], td[t, 1], ws[t, 1]]), "\\\\")
