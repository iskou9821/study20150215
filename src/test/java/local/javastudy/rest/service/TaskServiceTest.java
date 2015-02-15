package local.javastudy.rest.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat; //Assertのために手動追加
import static org.junit.Assert.assertTrue;
import local.javastudy.persist.entity.Task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//Assertのために手動追加

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-ut.xml")
public class TaskServiceTest {
	@Autowired TaskService svc;
	
	@Test
	public void 新規タスクを追加する() {
		Task t = new Task();
		t.setTitle("タイトル");
		t.setContent("コンテンツ");
		t.setOrd(10);
		
		long id = svc.save(t);
		assertTrue(id > 0l);
	}
	
}
