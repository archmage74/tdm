package tdm.i2p.db.protime;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import tdm.i2p.db.imos.IMOSPart;
import tdm.i2p.db.imos.IMOSPartProfile;

public class ProtimePart {
	public String PROJEKT_NO;
	public int HPOS;
	public int SPOS;
	public int LFD_NO;
	public int LFD_NO_PROJ_POS;
	public String KANTE_V_ST = "2";
	public String KANTE_H_ST = "2";
	public String KANTE_L_ST = "2";
	public String KANTE_R_ST = "2";
	public String KANTE_V_VN = "N";
	public String KANTE_H_VN = "N";
	public String KANTE_L_VN = "N";
	public String KANTE_R_VN = "N";
	public String STAERKE = "19";
	public String KANTE_V_B = "19";
	public String KANTE_H_B = "19";
	public String KANTE_L_B = "19";
	public String KANTE_R_B = "19";
	public String BEZEICHNUNG;
	public String BESCHREIBUNG;
	public String MATERIAL;
	public String LAENGE;
	public String BREITE;
	public String BELAG_A;
	public String BELAG_I;
	public String KANTE_V; 
	public String KANTE_H;
	public String KANTE_L;
	public String KANTE_R;
	public String KANTE_V_L;
	public String KANTE_H_L;
	public String KANTE_L_L;
	public String KANTE_R_L;
	public int LO = 6;
	public int MO = 3;
	public int RO = 6;
	public int MR = 3;
	public int RU = 6;
	public int MU = 3;
	public int LU = 6;
	public int ML = 3;
	public String MENGE;
	public int KR_SAUM = 0;
	public int KL_SAUM = 0;
	public int KH_SAUM = 0;
	public int KV_SAUM = 0;
	public String BK_NO = "LEER";
	
	public void setManualParams(String projectNo, int hPos) {
		PROJEKT_NO = projectNo;
		HPOS = hPos;
	}
	
	public void initFromIMOSPart(IMOSPart imosPart) {
		try {
			DecimalFormatSymbols symbols = new DecimalFormatSymbols();
			symbols.setDecimalSeparator(',');
			symbols.setGroupingSeparator('.');
			DecimalFormat df = new DecimalFormat("###.####");
			
			BEZEICHNUNG = imosPart.getName1();
			BESCHREIBUNG = imosPart.getBarcode();
			MATERIAL = imosPart.getMatId();
			LAENGE = df.format(imosPart.getFLeng());
			BREITE = df.format(imosPart.getFWidth());
			BELAG_A = imosPart.getSurfTId();
			SPOS = imosPart.getSPosEntry().getSPos();
			LFD_NO_PROJ_POS = imosPart.getSPosEntry().getSPos();
			if (IMOSPart.NO_SURFACE.equals(BELAG_A)) {
				BELAG_A = null;
			}
			BELAG_I = imosPart.getSurfBId();
			if (IMOSPart.NO_SURFACE.equals(BELAG_I)) {
				BELAG_I = null;
			}
			MENGE = df.format(imosPart.getCnt());
			
			IMOSPartProfile profile;
			profile = imosPart.getProfile(IMOSPartProfile.POS_V);
			if (profile != null && !profile.getPrfId().equals(IMOSPartProfile.NO_PROFILE)) {
				int profileType = profileType(profile.getPrfb());
				if (profileType != -1) {
					KANTE_V = profile.getPrfId();  
					KANTE_V_L = df.format(profile.getPrfLen());
					MU = profileType;
				}
			}
			profile = imosPart.getProfile(IMOSPartProfile.POS_R);
			if (profile != null && !profile.getPrfId().equals(IMOSPartProfile.NO_PROFILE)) {
				int profileType = profileType(profile.getPrfb());
				if (profileType != -1) {
					KANTE_R = profile.getPrfId();  
					KANTE_R_L = df.format(profile.getPrfLen());
					MR = profileType;
				}
			}
			profile = imosPart.getProfile(IMOSPartProfile.POS_H);
			if (profile != null && !profile.getPrfId().equals(IMOSPartProfile.NO_PROFILE)) {
				int profileType = profileType(profile.getPrfb());
				if (profileType != -1) {
					KANTE_H = profile.getPrfId();  
					KANTE_H_L = df.format(profile.getPrfLen());
					MO = profileType(profile.getPrfb());
				}
			}
			profile = imosPart.getProfile(IMOSPartProfile.POS_L);
			if (profile != null && !profile.getPrfId().equals(IMOSPartProfile.NO_PROFILE)) {
				int profileType = profileType(profile.getPrfb());
				if (profileType != -1) {
					KANTE_L = profile.getPrfId();  
					KANTE_L_L = df.format(profile.getPrfLen());
					ML = profileType(profile.getPrfb());
				}
			}
			
			RU = profileCorner(imosPart.getProfile(
					IMOSPartProfile.POS_V).getPrfp(), 
					imosPart.getProfile(IMOSPartProfile.POS_V),
					imosPart.getProfile(IMOSPartProfile.POS_R),
					false);
			RO = profileCorner(imosPart.getProfile(
					IMOSPartProfile.POS_R).getPrfp(), 
					imosPart.getProfile(IMOSPartProfile.POS_H),
					imosPart.getProfile(IMOSPartProfile.POS_R), 
					true);
			LO = profileCorner(imosPart.getProfile(
					IMOSPartProfile.POS_H).getPrfp(), 
					imosPart.getProfile(IMOSPartProfile.POS_H),
					imosPart.getProfile(IMOSPartProfile.POS_L), 
					false);
			LU = profileCorner(imosPart.getProfile(
					IMOSPartProfile.POS_L).getPrfp(), 
					imosPart.getProfile(IMOSPartProfile.POS_V),
					imosPart.getProfile(IMOSPartProfile.POS_L), 
					true);
			
		} catch (NullPointerException npe) {
			throw new RuntimeException("could not convert imospart: " + imosPart, npe);
		}

	}
	
	private int profileCorner(int prfb, IMOSPartProfile longProfile, IMOSPartProfile crossProfile, boolean isROorLU) {
		int value = 0;
		if (longProfile == null && crossProfile == null) {
			value = 6;
		} else if (longProfile == null) {
			value = 4; // abschluss des quer profils
		} else if (crossProfile == null) {
			value = 3; // abschluss des laengsprofils
		} else {
			switch (prfb) {
			case 3: // kante lang in IMOS
				if (isROorLU) {
					value = 1; 
				} else {
					value = 2;
				}
				break;
			case 2: // kante kurz in IMOS
				if (isROorLU) {
					value = 2; 
				} else {
					value = 1;
				}
				break;
			case 1:
				value = 5;
				break;
			default:
				value = 6;
			}
		}
		return value;
	}
	
	private int profileType(int prfp) {
		int value = 0;
		switch (prfp) {
		case 11: // kein uebergang
			value = 3;
			break;
		case 12: // kante nach belag
			value = 1;
			break;
		case 13: // kante vor belag
			value = 2;
			break;
		default: // default = kein uebergang
			value = -1;
		}

		return value;
	}
	
}
