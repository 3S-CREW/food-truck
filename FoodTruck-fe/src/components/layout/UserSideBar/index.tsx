import * as S from '@/components/layout/UserSideBar/style';
import { colors } from '@toss/tds-colors';
import { Asset, IconButton, ListRow, Text, TextButton } from '@toss/tds-mobile';

interface Props {
  isOpen: boolean;
  onClose: () => void;
}

const Sidebar = ({ isOpen, onClose }: Props) => {
  return (
    <>
      {/* 배경 어둡게 처리 (Overlay) */}
      <S.Overlay isOpen={isOpen} onClick={onClose} />

      {/* 슬라이드 메뉴 */}
      <S.Drawer isOpen={isOpen}>
        {/* 닫기 버튼 (우측 상단) */}
        <S.CloseButton onClick={onClose}>
          <IconButton
            name='icon-x-ios'
            variant='clear'
            iconSize={24}
            color={colors.grey500}
            aria-label='좌측 탭 닫기'
          />
        </S.CloseButton>

        {/* 프로필 영역 */}
        <S.ProfileSection>
          <ListRow
            verticalPadding='small'
            horizontalPadding='small'
            left={
              <Asset.Icon
                name='icon-user-circle-grey-fill'
                frameShape={{
                  height: 40,
                  width: 40,
                }}
                color={colors.blue500}
              />
            }
            contents={
              <ListRow.Texts type='2RowTypeA' top='오팔만' bottom='사용자' />
            }
            style={{ padding: 0 }}
          />
        </S.ProfileSection>

        {/* 메뉴 리스트 */}
        <S.MenuSection>
          <S.MenuRow>
            <Text typography='t5'>내 정보</Text>
          </S.MenuRow>
          <S.MenuRow>
            <Text typography='t5'>즐겨찾기</Text>
          </S.MenuRow>
        </S.MenuSection>

        {/* 하단 로그아웃 */}
        <S.LogoutSection>
          <TextButton size='medium' color={colors.red500}>
            <span style={{ display: 'flex', alignItems: 'center', gap: 8 }}>
              <Asset.Icon name='icon-out-mono' color={colors.red500} />
              로그아웃
            </span>
          </TextButton>
        </S.LogoutSection>
      </S.Drawer>
    </>
  );
};

export default Sidebar;
