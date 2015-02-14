package local.javastudy.rest.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue; //Assertのために手動追加
import static org.junit.Assert.assertThat; //Assertのために手動追加
import local.javastudy.rest.resource.model.HogeModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-ut.xml")
public class HogeServiceTest {
	@Autowired HogeService svc;
	
	@Test
	public void 新規データを追加する() {
		int size = svc.findAll().length;
		
		HogeModel saved = add();
				
		//保存したものを読み出せることを確認
		assertSaved(saved);
		
		//一行増えていることを確認
		int newSize = svc.findAll().length;
		assertThat(newSize, is(size + 1));
	}
	
	@Test
	public void 既存データを更新する() {
		HogeModel saved = add();
		
		saved.setMsg1("hogehogehoge");
		saved.setMsg2("piyopiyopiyo");
		
		//更新
		int size = svc.findAll().length;
		svc.save(saved);
		
		//保存したものを読み出せることを確認
		assertSaved(saved);
		
		int newSize = svc.findAll().length;
		assertThat(newSize, is(size));
	}
	
	@Test
	public void 既存データを削除する() {
		HogeModel saved = add();
		
		int size = svc.findAll().length;
		svc.delete(saved.getId());
		
		int newSize = svc.findAll().length;
		assertThat(newSize, is(size - 1));
	}
	
	private void assertSaved(HogeModel saved) {
		//保存したものを読み出せることを確認
		HogeModel loaded = svc.findOne(saved.getId());
		assertThat(saved.getId(), is(loaded.getId()));
		assertThat(saved.getMsg1(), is(loaded.getMsg1()));
		assertThat(saved.getMsg2(), is(loaded.getMsg2()));		
	}
	
	private HogeModel add() {
		HogeModel model = new HogeModel();
		model.setMsg1("hoge");
		model.setMsg2("piyo");
		HogeModel saved = svc.save(model);
		
		//処理の結果返ってきたModelの確認
		assertThat(saved.getId(), notNullValue());
		assertThat(saved.getMsg1(), is(model.getMsg1()));
		assertThat(saved.getMsg2(), is(model.getMsg2()));
		
		return saved;
	}
}
