package local.javastudy.rest.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat; //Assertのために手動追加
import static org.junit.Assert.assertTrue;

import java.util.List;

import local.javastudy.persist.entity.Task;

import org.junit.After;
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
	
	@After
	public void clear(){
		svc.clear();
	}
	
	@Test
	public void 新規タスクを追加する() {
		Task t = generateTask("タイトル","コンテンツ",10,false);
		long id = svc.save(t);
		assertTrue(id > 0l);
	}
	@Test
	public void 最新のタスクを取得する(){
		Tasks tasks = new Tasks();
		
		Task latest = svc.loadLatest();
		
		assertThat(latest.getId(),is(tasks.third.getId()));
	}
	@Test
	public void 最初のタスクを取得する(){
		Tasks tasks = new Tasks();
		
		Task first = svc.loadOldest();
		
		assertThat(first.getId(),is(tasks.first.getId()));
	}
	@Test
	public void 有効な全件を取得する(){
		Tasks tasks = new Tasks();
		
		List<Task> entity = svc.getValidTasks();
		final int MAX = 3;
		assertThat(entity.size(),is(MAX));
		//最初と最後があえばOK
		assertThat(entity.get(0).getId(),is(tasks.first.getId()));
		assertThat(entity.get(MAX-1).getId(),is(tasks.third.getId()));
		
	}

	//private utility method

	private Task generateTask(String title, String content, int ord, boolean save) {
		Task t = new Task();
		t.setTitle(title);
		t.setContent(content);
		t.setOrd(ord);
		if(save){
			svc.save(t);
		}
		return t;
	}
	
	private class Tasks{
		Task first;
		Task second;
		Task third;
		Tasks(){
			first = generateTask("first","first",11,true);
			second = generateTask("second","second",12,true);
			third = generateTask("third","third",13,true);
		}
		
	}
}
