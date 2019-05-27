# AppsMonitor
Tracks the other Apps used by User, during its Life-Cycle.

# Support
The App uses two approaches based on the Device OS :-
1)  Lolipop & Above
    Accessibility-Service: Monitoring WINDOW_CHANGED_STATES.
2)  Below Lolipop
    Periodically monitoring Tasks Running on Top of Activity Stack .
