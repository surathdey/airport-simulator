package com.test.loyaltyone.problem.airportsim.runway;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import com.loyaltyone.problem.airportsim.constants.BeanConstants;
import com.loyaltyone.problem.airportsim.model.FlightForLandNDepart;
import com.loyaltyone.problem.airportsim.model.FlightsInBlockingQueue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/config/context.xml")
public class RunwaySimulatorTest {

	@Autowired
	AsyncEventBus eventBus;
	
	@Autowired
	FlightsInBlockingQueue bqueue;
	
	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Autowired
	//protected ApplicationContext ac;
	
	@Test
	public void shouldCatchAllImportantBusinessEvents() {
		
		DeadEventListener deadEventsCollector =  new DeadEventListener();
		eventBus.register(deadEventsCollector);

        // when
        eventBus.post(bqueue);
        eventBus.post(bqueue);

        // then
        assertThat(deadEventsCollector.getEvents().isEmpty(),is(true));
	}
	
	private class DeadEventListener {

        private Set<DeadEvent> events = new HashSet<DeadEvent>();

        @Subscribe
        public void fetchDeadEvents(DeadEvent event) {
            events.add(event);
        }

        public Set<DeadEvent> getEvents() {
            return events;
        }
    }

}
