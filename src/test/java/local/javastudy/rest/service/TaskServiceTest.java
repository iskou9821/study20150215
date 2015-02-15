package local.javastudy.rest.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat; //Assertのために手動追加
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import local.javastudy.persist.entity.Task;

import org.codehaus.jackson.map.ObjectMapper;
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
	
	@Test
	public void タスクを削除する() {
		Tasks tasks = new Tasks();
		
		Task latest = svc.loadLatest();
		assertThat(latest.getId(), is(tasks.third.getId()));
		
		long id = svc.delete(latest);
		assertThat(latest.getId(), is(id));
		
		latest = svc.loadLatest();
		assertThat(latest.getId(), is(tasks.second.getId()));
	}
	
	@Test
	public void タスクの順番を入れ替える() {
		Tasks tasks = new Tasks();
		
		svc.changeOrder(tasks.first, tasks.third);
		
		Task latest = svc.loadLatest();
		Task oldest = svc.loadOldest();
		assertThat(latest.getId(), is(tasks.first.getId()));
		assertThat(oldest.getId(), is(tasks.third.getId()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 永続化していないデータでは順番を入れ替えられない() {
		Tasks tasks = new Tasks();
		
		Task t = generateTask("test", "テスト", 0, false);
		
		svc.changeOrder(tasks.first, t);
	}
	
	@Test
	public void すべての有効なデータをJson形式にする(){
		Tasks tasks = new Tasks();		
		String json = svc.getValidTasksAsJson();
		
		assertTrue(json.length() > 0);
		System.out.println(json);
		List<Task> taskList = svc.getValidTasks();
		Task[] taskArrays = taskList.toArray(new Task[0]);
		ObjectMapper mapper = new ObjectMapper();
		try {
			assertThat(taskArrays.length,is(mapper.readValue(json, Task[].class).length));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
