# AppsMonitor
Tracks the other Apps used by User, during its Life-Cycle.

# Support
The App uses two approaches based on the Device OS :-
1) Monitoring WINDOW_CHANGED_STATES via Accessibility-Service (APIs >= LOLLIPOP).
2) Periodically monitoring Tasks Running on Top of Activity Stack (APIs < LOLLIPOP).
