/**
 * Copyright (c) 2011 committers of YAKINDU and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of YAKINDU - initial API and implementation
 */
package org.yakindu.sct.simulation.core.sexec.interpreter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.yakindu.sct.simulation.core.sruntime.ExecutionContext;

import com.google.inject.Singleton;

/**
 * Implementation of {@link ITimingService} interface using java.util.Timer
 * 
 * @author andreas muelder - Initial contribution and API
 * 
 */
@Singleton
public class DefaultTimingService implements ITimingService {

	private Timer timer;

	private Map<String, TimerTask> timerTasks;

	private Map<String, CycleTask> cycleTasks;

	public DefaultTimingService() {
		timer = new Timer();
		timerTasks = new HashMap<String, TimerTask>();
		cycleTasks = new HashMap<String, CycleTask>();
	}

	public void scheduleTimeEvent(ExecutionContext context, String eventName, boolean isPeriodical, long duration) {
		TimeEventTask timeEventTask = new TimeEventTask(context, eventName);
		timerTasks.put(eventName, timeEventTask);
		if (isPeriodical) {
			timer.scheduleAtFixedRate(timeEventTask, duration, duration);
		} else {
			timer.schedule(timeEventTask, duration);
		}
	}

	@Override
	public void scheduleCycleEvent(ExecutionContext context, String eventName, boolean periodical, long cycleCount) {
		CycleTask cycleTask = new CycleTask(context, eventName, periodical, cycleCount);
		cycleTasks.put(eventName, cycleTask);
	}

	@Override
	public void unscheduleCycleEvent(String eventName) {
		CycleTask cycleTask = cycleTasks.get(eventName);
		if (cycleTask != null)
			cycleTask.cancel();
	}

	@Override
	public void cycle() {
		Collection<CycleTask> values = cycleTasks.values();
		for (Iterator<CycleTask> iterator = values.iterator(); iterator.hasNext();) {
			CycleTask cycleTask = iterator.next();
			cycleTask.runCycle();
			if (cycleTask.isTerminated())
				iterator.remove();
		}
	}

	public void unscheduleTimeEvent(String eventName) {
		TimerTask timerTask = timerTasks.get(eventName);
		timerTask.cancel();
	}

	public class TimeEventTask extends TimerTask {

		private final ExecutionContext context;
		private final String eventName;

		public TimeEventTask(ExecutionContext context, String eventName) {
			this.context = context;
			this.eventName = eventName;
		}

		public void run() {
			context.getEvent(eventName).setScheduled(true);
		}
	}

	public class CycleTask {

		private final ExecutionContext context;
		private final String eventName;
		private final long cyclePeriod;
		private final boolean periodical;
		private long cycleCounter;

		public CycleTask(ExecutionContext context, String eventName, boolean periodical, long cyclePeriod) {
			super();
			this.context = context;
			this.eventName = eventName;
			this.cyclePeriod = cyclePeriod;
			this.cycleCounter = cyclePeriod;
			this.periodical = periodical;
		}

		public void cancel() {
			cycleCounter = -1;
		}
		public boolean isTerminated() {
			return cycleCounter == -1;
		}

		public void runCycle() {
			cycleCounter--;
			if (cycleCounter == 0) {
				context.getEvent(eventName).setScheduled(true);
				if (periodical) {
					cycleCounter = cyclePeriod;
				} else {
					cycleCounter = -1;
				}
			}
		}
	}

	public void pause() {
		throw new RuntimeException("Implement me");
	}

	public void resume() {
		throw new RuntimeException("Implement me");
	}

	public void stop() {
		Collection<TimerTask> values = timerTasks.values();
		for (TimerTask timerTask : values) {
			timerTask.cancel();
		}
		timer.cancel();
	}
}
