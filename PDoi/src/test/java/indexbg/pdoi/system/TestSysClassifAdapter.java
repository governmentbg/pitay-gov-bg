package indexbg.pdoi.system;

import java.util.List;

import com.indexbg.system.db.dto.SystemClassif;
import com.indexbg.system.utils.SysClassifUtils;

public class TestSysClassifAdapter {

	public static void main(String[] args) {
		
		
		
		
		try {
			List<SystemClassif> classif = new PDoiClassifAdapter(new SystemData()).createEKATTE(111L, 1L, -1L);
			SysClassifUtils.doSortClassifPrev(classif);
			for (SystemClassif item : classif) {
				for (int i = 0; i < item.getLevelNumber(); i++) {
					System.out.print("\t");
				}
				System.out.println(item.getTekst());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
