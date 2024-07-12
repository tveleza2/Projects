import numpy as np
import matplotlib.pyplot as plt
from scipy.optimize import NonlinearConstraint 
from scipy import optimize
from timeit import default_timer as timer
import plotly.graph_objects as go

def fmincon(minfunc, Constraints, Initial_values): 
    '''
    Variables:
    minfunc: function to minimize
    lb and ub: float variables indicating low and upper bounderies of the search range
    Cy: float variable indicating the initial phase factor curvature (seed)
    '''

    start = timer() #Start to count time
    # nlc = NonlinearConstraint(fun = minfunc, lb = lb, ub = ub)
    out = optimize.minimize(fun = minfunc, x0 = Initial_values, method='SLSQP', constraints=Constraints) #The 'SLSQP' is equivalent to the default 'interior-point' algorithm that MATLAB's 'fmincon' function uses.
    if out.success:
        print("Optimization successful!")
    else:
        print("Optimization failed.")
    Cy_opt = out.x
    print("Processing time FMC:", timer()-start) #Time for FMC execution
    return Cy_opt

# def nyquist(x,pixelpitch):
#     out = x[0]*pixelpitch+x[1]*pixelpitch[1]
#     return out
z = 3e-3
wvl = 6.33e-7
in_size = [64,64]
Magn = 5/2
ppitch = 3.3e-6 / Magn
coef = 2 * ppitch**2 * (Magn+Magn**2)/wvl
# uby = z*wvl/np.sqrt(4*ppitch[1]**2-wvl**2)

comp2 = lambda x: x[0]*in_size[0]*x[1]*in_size[1]
nyquistx = lambda x: x[0]
lbx = (coef * in_size[0]**2 /np.sqrt(z**2 + (1+Magn)**2 * ppitch**2 * in_size[0]**2))
nyquisty = lambda x: x[1]
lby = (coef * in_size[1]**2 /np.sqrt(z**2 + (1+Magn)**2 * ppitch**2 * in_size[1]**2)) 
x_constraints = NonlinearConstraint(nyquistx, lb = lbx, ub = np.inf)
y_constraints = NonlinearConstraint(nyquisty, lb = lby, ub = np.inf)
cns = [x_constraints,y_constraints]
# cns = x_constraints

# Cy = np.array(2*np.array(in_size))
Cy = [lbx,lby]
sol = fmincon(comp2,cns,Cy)
print(sol)
x=np.linspace(0,10,100)
y=x
[X,Y]=np.meshgrid(x,y,indexing='xy')
Z = X*Y
fig = go.Figure(data=go.Surface(z=Z,x=X,y=Y))
# fig.show()

